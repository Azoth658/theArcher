package theArcher.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theArcher.powers.NockedAndLoadedPower;

import java.util.ArrayList;
import java.util.List;

import static theArcher.TheArcher.CustomTags.SHOT;

public abstract class AbstractFatigueCard extends CustomCard {

    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("theArcher:FatigueCard");
    public int baseMisc;                // Base, does not lower as card fatigues
    public boolean upgradedMisc = false;
    public boolean isFatigued = false;
    public String fatigueEffect = "";    // A description of what value the fatigue effect changes.
    public int fatigueAmount = 0;
    public boolean linkFatigueAmountToMagicNumber = false; // Is the quickenAmount always equal to this card's magicNumber
    public int theArcherFatigueSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int theArcherFatigueBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedtheArcherFatigueSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean istheArcherFatigueSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)



    public AbstractFatigueCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        istheArcherFatigueSecondMagicNumberModified = false;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy(){
        AbstractCard c = super.makeStatEquivalentCopy();
        if (c.misc == 0) ((AbstractFatigueCard)c).onFatigued();
        return c;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(uiStrings.TEXT[2],this.fatigueEffect + this.getMiscValueText()));
        return tips;
    }

    public String getMiscValueText(){
        return uiStrings.TEXT[3] + this.misc + uiStrings.TEXT[4];
    }

    @Override
    public void applyPowers() {

        if (!this.isFatigued && ((this.baseMisc > 0 && this.misc <= 0) || (this.baseMisc < 0 && this.misc >= 0))){
            onFatigued();
        }

        // Prevents crash if calculating multidamage in a null room
        boolean tmp = this.isMultiDamage;
        if (AbstractDungeon.currMapNode == null){
            this.isMultiDamage = false;
        }
        // Only do base applyPowers for cards in hand. This causes their displayed stats to change based on in-battle effects.
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this)) super.applyPowers();
        this.isMultiDamage = tmp;

        if (linkFatigueAmountToMagicNumber){
            this.fatigueAmount = this.magicNumber;
        }
    }

    public void onFatigued(){
        if (this.isFatigued) return;

        this.isFatigued = true;
        this.misc = 0;
        this.applyPowers(); // updates damage/block values to 0 instead of negative if misc dips too low.
        this.initializeDescription();
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
    }

    protected void upgradeMisc(int amount){
        this.baseMisc += amount;
        this.misc += amount;
        this.upgradedMisc = true;
        applyPowers();
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedtheArcherFatigueSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            theArcherFatigueSecondMagicNumber = theArcherFatigueBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            istheArcherFatigueSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        theArcherFatigueBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        theArcherFatigueSecondMagicNumber = theArcherFatigueBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedtheArcherFatigueSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.hasTag(SHOT) && AbstractDungeon.player.hasPower(NockedAndLoadedPower.POWER_ID) && AbstractDungeon.player.getPower(NockedAndLoadedPower.POWER_ID).amount > 0;
    }
}
