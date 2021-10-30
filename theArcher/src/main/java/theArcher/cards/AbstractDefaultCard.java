package theArcher.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theArcher.powers.FreeSkillPower;
import theArcher.powers.NockedAndLoadedPower;

import static theArcher.TheArcher.CustomTags.SHOT;

public abstract class AbstractDefaultCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    public int theArcherSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int theArcherBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedtheArcherSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean istheArcherSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)
    public int freeShotCount;

    public AbstractDefaultCard(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        istheArcherSecondMagicNumberModified = false;
    }


    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedtheArcherSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            theArcherSecondMagicNumber = theArcherBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            istheArcherSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        theArcherBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        theArcherSecondMagicNumber = theArcherBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedtheArcherSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.hasTag(SHOT) && AbstractDungeon.player.hasPower(NockedAndLoadedPower.POWER_ID) && AbstractDungeon.player.getPower(NockedAndLoadedPower.POWER_ID).amount > 0 || AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hasPower(FreeSkillPower.POWER_ID) && this.type == CardType.SKILL;
    }

}