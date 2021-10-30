package theArcher.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import theArcher.characters.TheArcher;
import theArcher.powers.EmptyQuiverPower;
import theArcher.powers.HeldActionPower;
import theArcher.powers.TargetPracticePower;

import java.util.Iterator;

import static theArcher.TheArcher.makeCardPath;

public class HeldAction extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = theArcher.TheArcher.makeID(HeldAction.class.getSimpleName());
    public static final String IMG = makeCardPath("heldAction.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheArcher.Enums.COLOR_ORANGE;

    private static final int COST = 2;
    // /STAT DECLARATION/


    public HeldAction() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HeldActionPower(p)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBaseCost(1);
        }

    }
}