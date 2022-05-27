package net.simpletech.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.simpletech.util.Dropresults;
import net.simpletech.util.VoxelUtil;

import java.util.Random;

@SuppressWarnings("deprecation")
public class Sieve extends Block {

    public Sieve(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(SieveProperties.PROGRESS, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        // Set the available properties
        stateManager.add(SieveProperties.PROGRESS);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext entityContext) {
        // Set the bounding-box
        return VoxelUtil.createCuboid(0, 0, 0, 16, 16, 16);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        int currentState = state.get(SieveProperties.PROGRESS);
        int targetProgress;

        // Check if the player is holding dirt and the sieve is empty
        if (currentState == 0) {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.isOf(Items.DIRT) ) {
                if (!player.isCreative()) {
                    // Take a dirt-block from Survival mode players
                    stack.decrement(1);
                    player.setStackInHand(hand, stack);
                }
            } else {
                return ActionResult.PASS;
            }
        }

        // Update state, do drop if needed
        currentState = currentState + 1;
        if (currentState > 3) {
            // Do the drop of items
            if (shouldDrop(world.getRandom())) {
                doDropResult(world, pos);
            }
            targetProgress = 0;
        } else {
            targetProgress = currentState;
        }
        world.setBlockState(pos, state.with(SieveProperties.PROGRESS, targetProgress));

        return ActionResult.CONSUME;
    }

    public boolean shouldDrop(Random random) {
        return random.nextBoolean();
    }

    public void doDropResult(World world, BlockPos pos) {
        Item randomItem = Dropresults.getRandomItem(Dropresults.ITEMS, world.getRandom());
        if (randomItem != null) {
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(randomItem, 1));
        }
    }
}
