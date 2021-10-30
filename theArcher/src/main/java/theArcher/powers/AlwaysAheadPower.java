package theArcher.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcher.TheArcher;
import theArcher.util.TextureLoader;

import static theArcher.TheArcher.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class AlwaysAheadPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = TheArcher.makeID("AlwaysAheadPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public AlwaysAheadPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;

        // We load those textures here.
        //this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        //this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.loadRegion("firebreathing");

        updateDescription();
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AlwaysAheadPower(owner, amount);
    }
}
