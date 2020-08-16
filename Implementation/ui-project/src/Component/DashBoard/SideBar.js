import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styles from './static/styles.module.css';
import config from '../../asset/config.json'

function SideBar(props) {
    const [pageTitle, setPageTitle] = useState("");
    useEffect(() => {
        if(config.dashboard.find(x => x.url === window.location.pathname)){
            setPageTitle(config.dashboard.find(x => x.url === window.location.pathname).title);
        }
        else{
            const filteredItems = config.dashboard.filter(item => item.url !== '/dashboard')
            setPageTitle(filteredItems.find(x => window.location.pathname.includes(x.url)).title);
        }
        
    }, []);

    function resetPageTitle(path) {
        if(config.dashboard.find(x => x.url === path)){
            setPageTitle(config.dashboard.find(x => x.url === path).title);
        }
        else{
            const filteredItems = config.dashboard.filter(item => item.url !== '/dashboard')
            setPageTitle(filteredItems.find(x => path.includes(x.url)).title);
        }
        
    }

    function renderButton() {
        let renderItem = [];
        config.dashboard.forEach((element, index) => {
            if(element.is_display){
                renderItem.push(
                    <div key={index} className={[styles.marginLeftSidebar, styles.containerButtonSideBar].join(" ")}><Link to={element.url} className={[styles.myLink, styles.myButtonSideBar].join(" ")} onClick={() => resetPageTitle(element.url)}><i className={element.icon_class}></i>&nbsp;{element.title}</Link></div>
                )
            }
        })
        return renderItem;
    }
    return (
        <div className={styles.mySidebar}>
            <div className={[styles.sideBarTitle].join(" ")}>
                <Link to="/dashboard" className={styles.myLink}>Trang quản lý</Link>
            </div>
            <hr className={styles.mySideBarHr} />

            <div className={[styles.marginLeftSidebar].join(" ")}>
                <span className={styles.mySidebarLabel}>{pageTitle}</span>
            </div>

            <hr className={styles.mySideBarHr} />

            {renderButton()}
        </div>
    );
}

export default SideBar;