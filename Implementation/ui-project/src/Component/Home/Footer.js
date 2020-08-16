import React from 'react';
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
import 'mdbreact/dist/css/mdb.css';
import styles from './static/styles.module.css'
import config from '../../asset/config.json'

function Footer(props) {
    function renderContact(){
        let renderItem = [];
        config.footer.contact.forEach((element, index) =>{
            renderItem.push(<li key={index} className="list-unstyled">
            <a href="#"><i className={[element.icon_class, styles.styleIcon].join(" ")}></i>&nbsp;{element.title}</a>
        </li>)
        })
        return renderItem;
    }

    function renderCommunity(){
        let renderItem = [];
        config.footer.community.forEach((element, index) =>{
            renderItem.push( <li key = {index} className="list-unstyled">
            <a href={element.url}><i className={[element.icon_class, styles.styleIcon].join(" ")}></i>&nbsp;{element.title}</a>
        </li>)
        })
        return renderItem;
    }

    return (
        <MDBFooter color="elegant-color-dark" className={["font-small pt-4 mt-4"].join(" ")}>
            <MDBContainer fluid className={["text-center text-md-left", styles.myNav, styles.myFooterContainer].join(" ")}>
                <MDBRow>
                    <MDBCol md="6">
                        <h5 className="title">Zannaghazi Library</h5>
                        <p>Không có người học dở, chỉ có người không biết đọc sách.</p>
                    </MDBCol>
                    <MDBCol md="3">
                        <h5 className="title">Thông tin liên hệ</h5>
                        <ul>
                            {renderContact()}
                        </ul>
                    </MDBCol>
                    <MDBCol md="3">
                        <h5 className="title">Cộng đồng</h5>
                        <ul>
                            {renderCommunity()}
                        </ul>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
            <div className="footer-copyright text-center py-3">
                <MDBContainer fluid>
                    <span>Phát triển bởi nhóm Sinh viên năm 6 trường Đại học Khoa học Tự nhiên</span>
                </MDBContainer>
            </div>
        </MDBFooter>
    );
}

export default Footer;