package theArcher.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theArcher.cards.AbstractFatigueCard;
import theArcher.cards.AbstractPreparedCard;

import static theArcher.TheArcher.makeID;

public class theArcherPreparedSecondMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("theArcherPreparedSecondMagic");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractPreparedCard) card).istheArcherPreparedSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractPreparedCard) card).theArcherPreparedSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractPreparedCard) card).theArcherPreparedBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractPreparedCard) card).upgradedtheArcherPreparedSecondMagicNumber;
    }
}