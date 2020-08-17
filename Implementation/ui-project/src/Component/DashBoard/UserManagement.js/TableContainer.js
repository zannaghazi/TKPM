import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import Moment from 'react-moment';
import config from '../../../asset/config.json'

function TableContainer(props) {
    const history = useHistory();

    const listItems = props.listReader.map((element, index) =>
        <tr key={index}>
            <td>{index + 1}</td>
            <td>{element.fullName}</td>
            <td>{element.gender}</td>
            <td>{element.bookName}</td>
            <td>{element.isbn}</td>
            <td>{element.publisher}</td>
            <td>{element.type}</td>
            <td>
            <Button variant="secondary" className={styles.myButtonDetail} onClick={(event) => createReceiveBill(event, element)}><i className="fa fa-angle-double-right"></i></Button>
                <Button variant="primary" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick={(event) => createReceiveBill(event, element)}><i className="fa fa-angle-double-left"></i></Button>
                <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick={() => createCompensationBook(element)}><i className="fa fa-exclamation"></i></Button>
            </td>
        </tr>
    );

    function createReceiveBill(event, data) {
        console.log("mydata", data);
        if (!data) {
            return;
        }
        history.push("/dashboard/edit_account/" + data.ID);
    }

    function createCompensationBook(data) {
        let url = config.severAPi.hostUrl + ":8083/system/delete_account/" + data.ID;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                if (dataReceive.result) {
                    alert("Xóa tài khoản thành công!")
                    props.onDeleteAccount(data);
                }
                else {
                    alert("Đã có lỗi xảy ra, vui lòng thử lại");
                }
            });
    }

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>#</th>
                    <th width={'15%'}>Tên</th>
                    <th width={'8%'}>Giới tính</th>
                    <th >Tên sách</th>
                    <th width={'10%'}>ISBN</th>
                    <th width={'15%'}>Nhà xuất bản</th>
                    <th width={'15%'}>Thể loại</th>
                    <th width={'10%'}>

                    </th>
                </tr>
            </thead>
            <tbody>
                {listItems}
            </tbody>
        </Table>
    );
}

export default TableContainer;