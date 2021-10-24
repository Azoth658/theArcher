package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import theArcher.actions.PreparedAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class EvasiveManoeuvres extends AbstractPreparedCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(EvasiveManoeuvres.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("evasiveManoeuvres.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 3;
    private static final int BUFFER = 2;
    private static final int PREPARED = 1;

    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public EvasiveManoeuvres() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = BUFFER;
        this.theArcherPreparedBaseSecondMagicNumber = this.theArcherPreparedSecondMagicNumber = this.misc;
        this.preparedAmount = PREPARED;
        this.magicNumber = PREPARED;
        this.baseMagicNumber = magicNumber;
        linkPreparedAmountToMagicNumber = true;
        this.preparedEffect = EXTENDED_DESCRIPTION[0];
        this.exhaust = true;
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
        this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, this.theArcherPreparedSecondMagicNumber), this.theArcherPreparedSecondMagicNumber));
    }

    @Override
    public void applyPowers() {
        this.theArcherPreparedBaseSecondMagicNumber = this.theArcherPreparedSecondMagicNumber = this.misc;
        if(this.theArcherPreparedSecondMagicNumber<0){
            this.theArcherPreparedSecondMagicNumber = 0;
        }
        super.applyPowers();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}
