package theArcher.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArcher.TheArcher.CustomTags.SHOT;

public class MoveCardsOfTagHandAction extends AbstractGameAction {
    private final AbstractCard.CardTags tags;
    private final boolean setCostToZero;

    public MoveCardsOfTagHandAction(AbstractCard.CardTags tags) {
        this(tags, 1, false);
    }

    public MoveCardsOfTagHandAction(AbstractCard.CardTags tags, int amount) {
        this(tags, amount, false);
    }

    public MoveCardsOfTagHandAction(AbstractCard.CardTags tags, int amount, boolean setCostToZero) {
        this.tags = tags;
        this.amount = amount;
        this.setCostToZero = setCostToZero;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty() || amount <= 0) {
            this.isDone = true;
            return;
        }
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(SHOT)) {
                tmp.addToRandomSpot(c);
            }
        }

        if (tmp.size() == 0) {
            this.isDone = true;
            return;
        }

        if (amount > tmp.size()) {
            amount = tmp.size();
        }

        tmp.shuffle();

        for (int i = 0 ; i < amount ; i++) {
            AbstractCard card = tmp.getBottomCard();
            tmp.removeCard(card);
            if (AbstractDungeon.player.hand.size() + i >= BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(card);
                AbstractDungeon.player.createHandIsFullDialog();
            } else {
                if(setCostToZero) {
                    card.setCostForTurn(0);
                }
                card.unhover();
                card.lighten(true);
                card.setAngle(0.0F);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.current_x = CardGroup.DRAW_PILE_X;
                card.current_y = CardGroup.DRAW_PILE_Y;
                AbstractDungeon.player.drawPile.removeCard(card);
                AbstractDungeon.player.hand.addToTop(card);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }
        }

        this.isDone = true;
    }
}