package theArcher.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcher.characters.TheArcher;
import theArcher.powers.AlwaysAheadPower;
import theArcher.powers.ReadyAndWaitingPower;

import static theArcher.TheArcher.makeCardPath;

public class AlwaysAhead extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(AlwaysAhead.class.getSimpleName());
    public static final String IMG = makeCardPath("alwaysAhead.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 2;
    private static final int UPGRADE_MAGIC = 4;
    // /STAT DECLARATION/


    public AlwaysAhead() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlwaysAheadPower(p, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}