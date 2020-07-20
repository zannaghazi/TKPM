import React from 'react';
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
// import '@fortawesome/fontawesome-free/css/all.min.css'; 
// import 'bootstrap-css-only/css/bootstrap.min.css'; 
import 'mdbreact/dist/css/mdb.css';
import styles from './static/styles.module.css'

function Footer(props) {
    return (
        <MDBFooter color="elegant-color-dark" className={["font-small pt-4 mt-4"].join(" ")}>
            <MDBContainer fluid className={["text-center text-md-left", styles.myNav].join(" ")}>
                <MDBRow>
                    <MDBCol md="6">
                        <h5 className="title">Zannaghazi Library</h5>
                        <p>Không có người học dở, chỉ có người không biết đọc sách.</p>
                    </MDBCol>
                    <MDBCol md="3">
                        <h5 className="title">Thông tin liên hệ</h5>
                        <ul>
                            <li className="list-unstyled">
                                <a href="#!"><i class={["fa fa-phone", styles.styleIcon].join(" ")}></i>&nbsp;0936252722</a>
                            </li>
                            <li className="list-unstyled">
                                <a href="#!"><i class={["fa fa-envelope-open", styles.styleIcon].join(" ")}></i>&nbsp;nthoang1996@gmail.com</a>
                            </li>
                            <li className="list-unstyled">
                                <a href="#!"><i class={["fa fa-map-marker", styles.styleIcon].join(" ")}></i>&nbsp;33/29 ĐHT 21 phường Đông Hưng Thuận quận 12 TP HCM</a>
                            </li>
                            <li className="list-unstyled">
                                <a href="#!"><i class={["fa fa-building", styles.styleIcon].join(" ")}></i>&nbsp;Trường Đại học Khoa học Tự nhiên</a>
                            </li>
                        </ul>
                    </MDBCol>
                    <MDBCol md="3">
                        <h5 className="title">Cộng đồng</h5>
                        <ul>
                            <li className="list-unstyled">
                                <a href="#!">Facebook Page</a>
                            </li>
                            <li className="list-unstyled">
                                <a href="#!">Youtube Channel</a>
                            </li>
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