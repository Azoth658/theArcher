package theArcher.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SadisticPower;
import theArcher.TheArcher;
import theArcher.util.TextureLoader;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeRelicOutlinePath;
import static theArcher.TheArcher.makeRelicPath;

public class BarbedArrowhead extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = TheArcher.makeID("BarbedArrowhead");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("barbedArrow.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("barbedArrowOutline.png"));

    public BarbedArrowhead() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SadisticPower(AbstractDungeon.player, 2), 2));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
