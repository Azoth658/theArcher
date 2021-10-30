package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theArcher.characters.TheArcher;
import theArcher.powers.EnchantedArrowsPower;
import theArcher.powers.TargetedPower;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class PointBlankShot extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(PointBlankShot.class.getSimpleName());
    public static final String IMG = makeCardPath("pointBlankShot.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 3;
    // STAT DECLARATION

    public PointBlankShot() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 40;
        tags.add(SHOT);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        this.addToBot(new ApplyPowerAction(p, p, new EntanglePower(p)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
                this.upgradeName();
                this.upgradeDamage(10);
                initializeDescription();
        }
    }
}