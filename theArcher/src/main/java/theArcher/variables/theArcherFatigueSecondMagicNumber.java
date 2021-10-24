package theArcher.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theArcher.cards.AbstractFatigueCard;

import static theArcher.TheArcher.makeID;

public class theArcherFatigueSecondMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("theArcherFatigueSecondMagic");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractFatigueCard) card).istheArcherFatigueSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractFatigueCard) card).theArcherFatigueSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractFatigueCard) card).theArcherFatigueBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractFatigueCard) card).upgradedtheArcherFatigueSecondMagicNumber;
    }
}