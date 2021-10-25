package theArcher.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theArcher.actions.FatigueAction;
import theArcher.characters.TheArcher;

import static theArcher.TheArcher.CustomTags.SHOT;
import static theArcher.TheArcher.makeCardPath;

public class PointBlankShot extends AbstractFatigueCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(PointBlankShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = makeCardPath("pointBlankShot.png");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 0;
    private static final int DAMAGE = 20;
    private static final int BASE_VULN = 1;
    private static final int UPGRADE_VULN = 2;
    private static final int FATIGUE = 10;
    private static final int FATIGUE_UPGRADE = -4;

    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private int baseVulnAmount;
    private int vulnAmount;

    // /STAT DECLARATION/

    public PointBlankShot() {
        super(ID, NAME, IMG, COST,DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMisc = this.misc = DAMAGE;
        this.baseDamage = this.damage = this.misc;
        this.fatigueAmount = FATIGUE;
        this.magicNumber = FATIGUE;
        this.baseMagicNumber = magicNumber;
        this.fatigueEffect = EXTENDED_DESCRIPTION[0];
        this.baseVulnAmount = this.vulnAmount = BASE_VULN;
        tags.add(SHOT);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FatigueAction(this));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.vulnAmount,false), this.vulnAmount));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, this.vulnAmount,false), this.vulnAmount));
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
            this.baseVulnAmount = this.vulnAmount = UPGRADE_VULN;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
