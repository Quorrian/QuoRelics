package quorelics.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import quorelics.cards.Bread;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static quorelics.QuoRelicsMod.makeID;

public class TeleporterRelic extends BaseRelic {
    public static final String ID = makeID("Teleporter");
    private final Bread breadCard = new Bread();

    //for character specific relics
    public TeleporterRelic() {
        super(ID, "teleporter", RelicTier.RARE, LandingSound.CLINK);
        setTooltips();
    }

    public void setTooltips() {
        description = DESCRIPTIONS[0];
        tips.clear();
        tips.add(new CardPowerTip(breadCard));
        initializeTips();
    }

    @Override
    public void onPlayCard(AbstractCard playedCard, AbstractMonster m) {
        if (!playedCard.exhaust && playedCard.type != AbstractCard.CardType.POWER)
            return;
        boolean inDeck = AbstractDungeon.player.masterDeck.group.stream().anyMatch(c -> c.uuid.equals(playedCard.uuid));
        if (!inDeck)
            return;
        flash();
        addToBot(new MakeTempCardInDiscardAction(playedCard, 1));
        addToBot(new MakeTempCardInDiscardAction(breadCard, 1));
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TeleporterRelic();
    }
}