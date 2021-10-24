package theArcher.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.actions.PreparedAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.makeCardPath;

public class BraceForImpact extends AbstractPreparedCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(BraceForImpact.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("braceForImpact.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 0;
    private static final int BLOCK = 4;
    private static final int PREPARED = 2;
    private static final int PREPARED_UPGRADE = 2;

    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // /STAT DECLARATION/

    public BraceForImpact() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = BLOCK;
        this.baseBlock = this.block = this.misc;
        this.preparedAmount = PREPARED;
        this.magicNumber = PREPARED;
        this.baseMagicNumber = magicNumber;
        linkPreparedAmountToMagicNumber = true;
        this.preparedEffect = EXTENDED_DESCRIPTION[0];
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
        this.addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = this.block = this.misc;
        if(this.baseBlock<0){
            this.baseBlock = 0;
        }
        super.applyPowers();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.preparedAmount += PREPARED_UPGRADE;
            this.magicNumber = preparedAmount;
            this.baseMagicNumber = magicNumber;
            initializeDescription();
        }
    }
}
