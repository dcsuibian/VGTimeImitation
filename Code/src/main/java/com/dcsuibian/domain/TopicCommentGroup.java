package com.dcsuibian.domain;

import java.util.ArrayList;
import java.util.List;

public class TopicCommentGroup {
    private TopicComment primaryComment;
    private List<TopicComment> secondaryComments;

    public TopicCommentGroup() {
    }

    public TopicCommentGroup(TopicComment primaryComment, List<TopicComment> secondaryComments) {
        this.primaryComment = primaryComment;
        this.secondaryComments = secondaryComments;
    }

    public TopicComment getPrimaryComment() {
        return primaryComment;
    }

    public void setPrimaryComment(TopicComment primaryComment) {
        this.primaryComment = primaryComment;
    }

    public List<TopicComment> getSecondaryComments() {
        return secondaryComments;
    }

    public void setSecondaryComments(List<TopicComment> secondaryComments) {
        this.secondaryComments = secondaryComments;
    }

    public static List<TopicCommentGroup> getTopicCommentGroupList(List<TopicComment> topicComments){
        var ans=new ArrayList<TopicCommentGroup>();
        for(TopicComment tc:topicComments){
            if(null==tc.getCommentGroupId()){
                var temp=new TopicCommentGroup();
                temp.primaryComment=tc;
                temp.secondaryComments=new ArrayList<TopicComment>();
                ans.add(temp);
            }
        }
        for(TopicComment tc:topicComments){
            if(null!=tc.getCommentGroupId()){
                for(int i=0;i<ans.size();i++){
                    if(ans.get(i).primaryComment.getId().equals(tc.getCommentGroupId())){
                        ans.get(i).secondaryComments.add(tc);
                    }
                }
            }
        }
        return ans;
    }
}
