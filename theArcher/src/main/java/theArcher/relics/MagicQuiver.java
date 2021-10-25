package theArcher.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.TheArcher;
import theArcher.util.TextureLoader;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeRelicOutlinePath;
import static theArcher.TheArcher.makeRelicPath;

public class MagicQuiver extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = TheArcher.makeID("MagicQuiver");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("magicQuiver.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("magicQuiverOutline.png"));

    public MagicQuiver() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    public void atBattleStart() {
        this.usedUp = false;
        this.grayscale = false;
    }

    public void atTurnStart() {
        this.usedUp = false;
        this.grayscale = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if(!this.usedUp) {
            if (c.hasTag(SHOT)) {
                this.flash();
                this.addToBot(new DrawCardAction(1));
                this.usedUp = true;
                this.grayscale = true;
            }
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
