package theArcher.cards;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.actions.PreparedAction;
import theArcher.actions.TrickShotMoveAction;
import theArcher.characters.TheArcher;
import theArcher.powers.HeldActionPower;
import theArcher.powers.MasteryFormPower;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class TrickShot extends AbstractPreparedCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(TrickShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("trickShot.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int PREPARED = 6;
    private static final int PREPARED_UPGRADE = 4;

    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public TrickShot() {
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
        if(!AbstractDungeon.player.hasPower(MasteryFormPower.POWER_ID)) {
            this.addToBot(new TrickShotMoveAction(this));
        }
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
            this.upgradeDamage(4);
            this.upgradeMisc(4);
            this.preparedAmount += PREPARED_UPGRADE;
            this.magicNumber = preparedAmount;
            this.baseMagicNumber = magicNumber;
            initializeDescription();
        }
    }
}
