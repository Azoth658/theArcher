package theArcher.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import theArcher.characters.TheArcher;
import theArcher.powers.FreeSkillPower;

import static theArcher.TheArcher.makeCardPath;

public class HiddenResolve extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(HiddenResolve.class.getSimpleName());
    public static final String IMG = makeCardPath("cower.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 1;


    // /STAT DECLARATION/


    public HiddenResolve() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            this.baseMagicNumber = 2;
            this.magicNumber = this.baseMagicNumber;
            this.theArcherBaseSecondMagicNumber = 1;
            this.theArcherSecondMagicNumber = this.theArcherBaseSecondMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new FreeSkillPower(p, theArcherBaseSecondMagicNumber), theArcherBaseSecondMagicNumber));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDefaultSecondMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}