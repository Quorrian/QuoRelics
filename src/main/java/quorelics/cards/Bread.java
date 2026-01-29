package quorelics.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;


public class Bread extends BaseCard {
    private static final String NAME_FOR_ID = "Bread";
    public String ID = makeID(NAME_FOR_ID);
    private static final String IMG_PATH = "QuoDispense/images/cards/bread.png";

    public Bread() {
        super(makeID(NAME_FOR_ID), 1, CardType.STATUS, CardTarget.SELF, CardRarity.SPECIAL, CardColor.COLORLESS, IMG_PATH);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
        } else {
            addToBot(new VFXAction(new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
        }
        addToBot(new DamageAction(p, new DamageInfo(p, this.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
    }

}