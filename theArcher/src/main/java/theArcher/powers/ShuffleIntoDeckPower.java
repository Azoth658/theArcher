package theArcher.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcher.TheArcher;
import theArcher.actions.ShuffleCardsIntoDeckAction;
import theArcher.util.TextureLoader;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class ShuffleIntoDeckPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = TheArcher.makeID("ShuffleIntoDeckPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private int basePower;

    public ShuffleIntoDeckPower(AbstractCreature owner, int numCards) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = numCards;

        // We load those textures here.

        this.loadRegion("retain");
        //this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        //this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
            this.addToTop(new ShuffleCardsIntoDeckAction(this.owner, this.amount));
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new ShuffleIntoDeckPower(owner, amount);
    }
}
