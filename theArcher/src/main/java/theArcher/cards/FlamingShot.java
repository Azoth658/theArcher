package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.actions.FatigueAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class FlamingShot extends AbstractFatigueCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(FlamingShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("flamingShot.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 14;
    private static final int BASE_EXHAUST = 1;
    private static final int UPGRADED_EXHAUST = 3;
    private static final int FATIGUE = 5;
    private static final int FATIGUE_UPGRADE = -2;

    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private int baseExhaustAmount;
    private int exhaustAmount;

    // /STAT DECLARATION/

    public FlamingShot() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = DAMAGE;
        this.baseDamage = this.damage = this.misc;
        this.fatigueAmount = FATIGUE;
        this.magicNumber = FATIGUE;
        this.baseMagicNumber = magicNumber;
        this.fatigueEffect = EXTENDED_DESCRIPTION[0];
        this.baseExhaustAmount = this.exhaustAmount = BASE_EXHAUST;
        tags.add(SHOT);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FatigueAction(this));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ExhaustAction(this.exhaustAmount, false, true, true));
    }

    @Override
    public void applyPowers() {
        this.baseDamage = this.damage = this.misc;
        if(this.baseDamage<0){
            this.baseDamage = 0;
        }
        super.applyPowers();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.fatigueAmount += FATIGUE_UPGRADE;
            this.magicNumber = fatigueAmount;
            this.baseMagicNumber = magicNumber;
            this.baseExhaustAmount = this.exhaustAmount = UPGRADED_EXHAUST;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
