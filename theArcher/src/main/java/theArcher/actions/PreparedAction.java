package theArcher.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcher.cards.AbstractFatigueCard;
import theArcher.cards.AbstractPreparedCard;
import theArcher.powers.ReadyAndWaitingPower;

public class PreparedAction extends AbstractGameAction {
    private AbstractCard card;
    private int miscPrepared;

    public PreparedAction(AbstractPreparedCard card) {
        this.miscPrepared = card.preparedAmount;
        this.card = card;
    }

    public void update() {

        this.card.superFlash(Color.GREEN);

        card.misc += this.miscPrepared;
        card.applyPowers();
        if(AbstractDungeon.player.hasPower(ReadyAndWaitingPower.POWER_ID)){
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(ReadyAndWaitingPower.POWER_ID).amount));
        }

        this.isDone = true;
    }
}