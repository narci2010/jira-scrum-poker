package net.congstar.jira.plugins.scrumpoker.action;

import net.congstar.jira.plugins.scrumpoker.data.ScrumPokerStorage;
import net.congstar.jira.plugins.scrumpoker.data.StoryPointFieldSupport;

/**
 * Confirm the chosen estimation and return to the associated issue.
 */
public class ConfirmEstimationAction extends ScrumPokerAction {

    private static final long serialVersionUID = 1L;
    private static final String PARAM_FINAL_VOTE = "finalVote";

    private final StoryPointFieldSupport storyPointFieldSupport;
    private final ScrumPokerStorage scrumPokerStorage;

    public ConfirmEstimationAction(StoryPointFieldSupport storyPointFieldSupport, ScrumPokerStorage scrumPokerStorage) {
        this.storyPointFieldSupport = storyPointFieldSupport;
        this.scrumPokerStorage = scrumPokerStorage;
    }

    @Override
    protected String doExecute() throws Exception {
        String issueKey = getHttpRequest().getParameter(PARAM_ISSUE_KEY);
        String finalVoteString = getHttpRequest().getParameter(PARAM_FINAL_VOTE);
        Integer finalVote = Integer.valueOf(finalVoteString);
        scrumPokerStorage.sessionForIssue(issueKey, currentUserKey()).confirm(finalVote);
        storyPointFieldSupport.save(issueKey, finalVote);
        return openIssue(issueKey);
    }

}
