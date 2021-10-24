package theArcher.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcher.TheArcher;
import theArcher.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.SELF_AND_ENEMY;
import static theArcher.TheArcher.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class RicochetPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = TheArcher.makeID("RicochetPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public RicochetPower(final AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.description = DESCRIPTIONS[0];
        type = PowerType.BUFF;
        this.isTurnBased = false;

        // We load those textures here.
        this.loadRegion("mastery");
        //this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        //this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(card.target == ENEMY || card.target == SELF_AND_ENEMY) {
            flash();
            for (AbstractMonster aM : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!aM.isDeadOrEscaped() && aM.hasPower(TargetedPower.POWER_ID) && aM != m) {
                    card.use(AbstractDungeon.player, aM);
                }
            }
        }
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new RicochetPower(owner);
    }
}
