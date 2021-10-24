package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import theArcher.actions.FatigueAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.makeCardPath;

public class BountyHunt extends AbstractFatigueCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(BountyHunt.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("bountyHunt.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 2;
    private static final int DAMAGE = 30;
    private static final int FATIGUE = 10;
    private static final int FATIGUE_UPGRADE = -5;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public BountyHunt() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = DAMAGE;
        this.baseDamage = this.damage = this.misc;
        this.fatigueAmount = FATIGUE;
        this.magicNumber = FATIGUE;
        this.baseMagicNumber = magicNumber;
        this.fatigueEffect = EXTENDED_DESCRIPTION[0];
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FatigueAction(this));
        this.addToBot(new GreedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.damage));

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
            upgradeDamage(5);
            this.fatigueAmount += FATIGUE_UPGRADE;
            this.magicNumber = fatigueAmount;
            this.baseMagicNumber = magicNumber;
            initializeDescription();
        }
    }
}
