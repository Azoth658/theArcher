package theArcher.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class TrickShotMoveAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard card;

    public TrickShotMoveAction(AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.card = card;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            this.p.hand.moveToDeck(card, false);
                        AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
        }

        this.tickDuration();
    }
}