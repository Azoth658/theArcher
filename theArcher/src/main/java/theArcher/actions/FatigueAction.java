package theArcher.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcher.cards.AbstractFatigueCard;
import theArcher.powers.MasteryFormPower;

public class FatigueAction extends AbstractGameAction {
    private AbstractCard card;
    private int miscFatigue;

    public FatigueAction(AbstractFatigueCard card) {
        if(!AbstractDungeon.player.hasPower(MasteryFormPower.POWER_ID)) {
            this.miscFatigue = -card.fatigueAmount;
            this.card = card;
        }
        else
            this.miscFatigue = 0;
            this.card = card;

    }

    public void update() {

        if(!AbstractDungeon.player.hasPower(MasteryFormPower.POWER_ID)) {
            this.card.superFlash(Color.RED);

            card.misc += this.miscFatigue;
            card.applyPowers();

            this.isDone = true;
        }
        else
            card.misc  += this.miscFatigue;
            card.applyPowers();
           this.isDone = true;
    }
}