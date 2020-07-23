import React from 'react';
import { Link } from 'react-router-dom';
import styles from './static/styles.module.css'

function SideBar(props) {
    return (
        <div className={styles.mySidebar}>
            <div className={[styles.sideBarTitle].join(" ")}>
                <Link to="/dashboard" className={styles.myLink}>Trang quản lý</Link>
            </div>
            <hr className={styles.mySideBarHr}/>

            <div className = {[styles.marginLeftSidebar].join(" ")}>
                    <span className={styles.mySidebarLabel}>Quản lý tài khoản</span>
            </div>

            <hr className={styles.mySideBarHr} />

            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-user"></i>&nbsp;Quản lý tài khoản</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-bookmark"></i>&nbsp;Quản lý sách</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-book"></i>&nbsp;Quản lý độc giả</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-upload"></i>&nbsp;Quản lý nhà xuất bản</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-list"></i>&nbsp;Quản lý thể loại sách</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-arrows-alt"></i>&nbsp;Quản lý mượn trả</Link></div>
            <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to="#" className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className="fa fa-file"></i>&nbsp;Lập báo cáo</Link></div>
        </div>
    );
}

export default SideBar;