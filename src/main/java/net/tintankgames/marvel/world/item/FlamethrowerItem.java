package net.tintankgames.marvel.world.item;

import com.google.common.collect.Lists;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.world.entity.projectile.Flame;
import org.joml.Math;

import java.util.List;
import java.util.stream.Collectors;

public class FlamethrowerItem extends SuitPowerItem implements ProjectileItem {
    public static final List<Ingredient> FUEL_PRIORITY_ORDER = Lists.newArrayList(
            Ingredient.of(ItemTags.COALS),
            Ingredient.of(Tags.Items.STORAGE_BLOCKS_COAL),
            Ingredient.of(ItemTags.PLANKS),
            Ingredient.of(Tags.Items.TOOLS),
            Ingredient.of(ItemTags.LOGS_THAT_BURN),
            Ingredient.of(Tags.Items.RODS_WOODEN),
            Ingredient.of(ItemTags.SAPLINGS)
    );

    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return FastColor.ARGB32.color(255, (int) Mth.lerp(getLerpValue(), 106.0F, 217.0F), 0);
    }

    private float getLerpValue() {
        float f = (float) (System.currentTimeMillis() % 500L) / 500.0F;
        float g = (float) (System.currentTimeMillis() % 1000L) / 500.0F;
        return f != g ? 1 - f : f;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(stack.getOrDefault(MarvelDataComponents.FUEL, 0) * 13.0F / stack.getOrDefault(MarvelDataComponents.FUEL_MAX, 800));
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if (p_41406_ instanceof Player player) {
            p_41404_.set(MarvelDataComponents.FUEL_MAX, player.isCreative() ? 800 : player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FUEL_MAX, 800));
            p_41404_.set(MarvelDataComponents.FUEL, player.isCreative() ? 800 : player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FUEL, 0));
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity living, ItemStack stack, int ticksLeft) {
        super.onUseTick(level, living, stack, ticksLeft);
        if (living instanceof Player player) {
            boolean flag = player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FUEL, 0) > 0 || player.isCreative();
            if (!flag && player.getInventory().hasAnyMatching(stack1 -> stack1.getBurnTime(RecipeType.SMELTING) > 0)) {
                List<ItemStack> fuelStacks = player.getInventory().items.stream().filter(stack1 -> stack1.getBurnTime(RecipeType.SMELTING) > 0 && !stack1.is(Items.LAVA_BUCKET)).collect(Collectors.toList());
                ItemStack fuelStack = ItemStack.EMPTY;
                for (Ingredient fuel : FUEL_PRIORITY_ORDER) {
                    for (ItemStack itemStack : fuelStacks) {
                        if (fuel.test(itemStack)) {
                            fuelStack = itemStack;
                            break;
                        }
                    }
                    if (!fuelStack.isEmpty()) break;
                }
                if (fuelStack.isEmpty()) {
                    fuelStack = fuelStacks.get(level.random.nextInt(fuelStacks.size()));
                }
                int fuelTime = fuelStack.getBurnTime(RecipeType.SMELTING) / 4;
                player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FUEL, fuelTime);
                player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FUEL_MAX, fuelTime);
                fuelStack.shrink(1);
                flag = true;
            }
            if (flag) {
                int i = this.getUseDuration(stack, living) - ticksLeft;
                if (!level.isClientSide() && i % 5 == 0)
                    level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS);
                if (!level.isClientSide) {
                    Flame flame = new Flame(level, player, stack, stack);
                    flame.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 2.0F);
                    level.addFreshEntity(flame);
                    for (int j = 0; j < 4; j++) {
                        level.addParticle(MarvelParticleTypes.EMISSIVE_FLAME.get(), flame.getX(), flame.getY(), flame.getZ(), 0, 0, 0);
                    }
                    if (!player.hasInfiniteMaterials()) {
                        player.getInventory().removeItem(stack);
                    }
                    if (player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FUEL, 0) > 0 && !player.isCreative()) {
                        player.getItemBySlot(EquipmentSlot.CHEST).update(MarvelDataComponents.FUEL, 1, fuel -> fuel - 1);
                    }
                }
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public Projectile asProjectile(Level p_338867_, Position p_338379_, ItemStack p_338543_, Direction p_338380_) {
        return new Flame(p_338867_, p_338379_.x(), p_338379_.y(), p_338379_.z(), p_338543_.copyWithCount(1), p_338543_.copyWithCount(1));
    }
}
