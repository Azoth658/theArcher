package theArcher.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcher.TheArcher;
import theArcher.util.TextureLoader;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class NockedAndLoadedPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = TheArcher.makeID("EmptyQuiverPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private int basePower;

    public NockedAndLoadedPower(final AbstractCreature owner, int amount, int basePower) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.basePower = basePower;
        if(amount > 1){
            this.description = DESCRIPTIONS[0] + "#b"+amount + DESCRIPTIONS[2];
        }
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.

        this.loadRegion("swivel");
        //this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        //this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(SHOT) && this.amount > 0){
            if((amount - 1) <=0){
                amount = 0;
            } else --amount;
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        amount = basePower;
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        if(amount > 1){
            this.description = DESCRIPTIONS[0] + "#b"+amount + DESCRIPTIONS[2];
        }
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new NockedAndLoadedPower(owner, amount, basePower);
    }
}
