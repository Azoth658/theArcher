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
import theArcher.actions.PreparedAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class PreparedShot extends AbstractPreparedCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(PreparedShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("preparedShot.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int PREPARED = 4;
    private static final int PREPARED_UPGRADE = 2;

    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public PreparedShot() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = DAMAGE;
        this.baseDamage = this.damage = this.misc;
        this.preparedAmount = PREPARED;
        this.magicNumber = PREPARED;
        this.baseMagicNumber = magicNumber;
        linkPreparedAmountToMagicNumber = true;
        this.preparedEffect = EXTENDED_DESCRIPTION[0];
        tags.add(SHOT);
    }

    @Override
    public void triggerOnEndOfPlayerTurn(){
        this.addToBot(new PreparedAction(this));
    }
    @Override
    public void onRetained(){
        this.addToBot(new PreparedAction(this));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
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
            this.upgradeDamage(2);
            this.preparedAmount += PREPARED_UPGRADE;
            this.magicNumber = preparedAmount;
            this.baseMagicNumber = magicNumber;
            initializeDescription();
        }
    }
}
