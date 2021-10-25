package theArcher.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import javax.swing.*;
import java.util.Iterator;

public class ShuffleCardsIntoDeckAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private AbstractCard card;

    public ShuffleCardsIntoDeckAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for (Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext();) {
                    c = (AbstractCard) var1.next();
                    this.p.hand.moveToDeck(c, true);
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("theArcher:ShuffleCardsIntoDeckAction");
        TEXT = uiStrings.TEXT;
    }
}
