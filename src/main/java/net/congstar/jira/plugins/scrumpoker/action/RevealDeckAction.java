package net.congstar.jira.plugins.scrumpoker.action;

import net.congstar.jira.plugins.scrumpoker.data.ScrumPokerStorage;

/**
 * Reveal all cards that are currently hidden for a specific issue.
 */
public class RevealDeckAction extends ScrumPokerAction {

    private static final long serialVersionUID = 1L;

    private final ScrumPokerStorage scrumPokerStorage;

    public RevealDeckAction(ScrumPokerStorage scrumPokerStorage) {
        this.scrumPokerStorage = scrumPokerStorage;
    }

    @Override
    protected String doExecute() throws Exception {
        String issueKey = getHttpRequest().getParameter(PARAM_ISSUE_KEY);
        scrumPokerStorage.sessionForIssue(issueKey, currentUserKey()).revealDeck();
        return openScrumPokerForIssue(issueKey);
    }

}
