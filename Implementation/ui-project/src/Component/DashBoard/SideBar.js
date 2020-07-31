import React from 'react';
import { Link } from 'react-router-dom';
import styles from './static/styles.module.css';
import config from '../../asset/config.json'

function SideBar(props) {
    function renderButton(){
        let renderItem = [];
        config.dashboard.forEach(element => {
            renderItem.push(
                <div className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to={element.url} className={[styles.myLink, styles.myButtonSideBar].join(" ")}><i className={element.icon_class}></i>&nbsp;{element.title}</Link></div>
            )
        })
        return renderItem;
    }
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

            {renderButton()}
        </div>
    );
}

export default SideBar;