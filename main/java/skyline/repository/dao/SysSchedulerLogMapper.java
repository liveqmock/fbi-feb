package skyline.repository.dao;

import skyline.repository.model.SysSchedulerLog;
import skyline.repository.model.SysSchedulerLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public interface SysSchedulerLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int countByExample(SysSchedulerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int deleteByExample(SysSchedulerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int insert(SysSchedulerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int insertSelective(SysSchedulerLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    List<SysSchedulerLog> selectByExample(SysSchedulerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int updateByExampleSelective(@Param("record") SysSchedulerLog record, @Param("example") SysSchedulerLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_SCHEDULER_LOG
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int updateByExample(@Param("record") SysSchedulerLog record, @Param("example") SysSchedulerLogExample example);
}