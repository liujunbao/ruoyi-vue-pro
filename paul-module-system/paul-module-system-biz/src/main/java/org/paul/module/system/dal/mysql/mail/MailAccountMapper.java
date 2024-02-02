package org.paul.module.system.dal.mysql.mail;

import org.paul.framework.common.pojo.PageResult;
import org.paul.framework.mybatis.core.mapper.BaseMapperX;
import org.paul.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.paul.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import org.paul.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername()));
    }

}
