package theArcher.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theArcher.actions.FatigueAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.makeCardPath;

public class LiquidCourage extends AbstractFatigueCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(LiquidCourage.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("liquidCourage.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;

    private static final int ARTIFACT = 2;

    private static final int FATIGUE = 1;
    private static final int FATIGUE_UPGRADE = -1;

    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public LiquidCourage() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = ARTIFACT;
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
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, theArcherFatigueSecondMagicNumber)));
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
            this.fatigueAmount += FATIGUE_UPGRADE;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
