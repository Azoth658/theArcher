package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theArcher.characters.TheArcher;
import theArcher.powers.TargetedPower;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class BoomerangShot extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(BoomerangShot.class.getSimpleName());
    public static final String IMG = makeCardPath("boomerangShot.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;
    // STAT DECLARATION

    public BoomerangShot() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 6;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        tags.add(SHOT);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m,p, this.magicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
                this.upgradeName();
                this.upgradeDamage(2);
                this.upgradeMagicNumber(2);
                initializeDescription();
        }
    }
}