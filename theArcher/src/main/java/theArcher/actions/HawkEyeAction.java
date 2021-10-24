package theArcher.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theArcher.powers.TargetedPower;

public class HawkEyeAction extends AbstractGameAction {
        private DamageInfo info;
        private int magicnumber;

        public HawkEyeAction(AbstractCreature target, DamageInfo info, int magicnumber) {
            this.info = info;
            this.magicnumber = magicnumber;
            this.setValues(target, info);
            this.actionType = ActionType.DAMAGE;
            this.startDuration = Settings.ACTION_DUR_FAST;
            this.duration = this.startDuration;
        }

        @Override
        public void update() {
            if (this.shouldCancelAction()) {
                this.isDone = true;
            } else {
                this.tickDuration();
                if (this.isDone) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY, false));
                    this.target.damage(this.info);
                    if (this.target.lastDamageTaken > 0) {
                        this.addToTop(new ApplyPowerAction(this.target,this.source, new TargetedPower(this.target,this.source,this.magicnumber)));
                        if (this.target.hb != null) {
                            this.addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
                        }
                    }

                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    } else {
                        this.addToTop(new WaitAction(0.1F));
                    }
                }

            }
        }
    }
