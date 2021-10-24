package theArcher.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.actions.FatigueAction;
import theArcher.characters.TheArcher;
import theArcher.powers.TargetedPower;

import static theArcher.TheArcher.makeCardPath;

public class MarkedForDeath extends AbstractFatigueCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(MarkedForDeath.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("markedForDeath.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;

    private static final int TARGETED = 5;
    private static final int TARGETED_UPGRADE = 7;

    private static final int FATIGUE = 3;
    private static final int FATIGUE_UPGRADE = -1;

    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public MarkedForDeath() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = TARGETED;
        this.theArcherFatigueBaseSecondMagicNumber = this.theArcherFatigueSecondMagicNumber = this.misc;
        this.fatigueAmount = FATIGUE;
        this.magicNumber = FATIGUE;
        this.baseMagicNumber = magicNumber;
        this.fatigueEffect = EXTENDED_DESCRIPTION[0];
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FatigueAction(this));
        this.addToBot(new ApplyPowerAction(m, p, new TargetedPower(m,p, theArcherFatigueSecondMagicNumber)));
    }

    @Override
    public void applyPowers() {
        this.theArcherFatigueBaseSecondMagicNumber = this.theArcherFatigueSecondMagicNumber = this.misc;
        if(this.theArcherFatigueBaseSecondMagicNumber <0){
            this.theArcherFatigueBaseSecondMagicNumber = 0;
        }
        super.applyPowers();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.baseMisc = this.misc = TARGETED_UPGRADE;
            this.theArcherFatigueBaseSecondMagicNumber = this.theArcherFatigueSecondMagicNumber = this.misc;
            this.fatigueAmount += FATIGUE_UPGRADE;
            this.magicNumber = this.fatigueAmount;
            this.baseMagicNumber = magicNumber;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
