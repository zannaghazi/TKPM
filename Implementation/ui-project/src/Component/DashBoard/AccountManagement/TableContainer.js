import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';
import config from '../../../asset/config.json'
import Moment from 'react-moment';

function TableContainer(props) {
    const history = useHistory();

    function editItem(event, data) {
        console.log("mydata", data);
        if (!data) {
            return;
        }
        history.push("/dashboard/edit_account/" + data.ID);
    }


    function deleteAccount(data) {
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

    const listItems = props.listAccount.map((element, index) =>
        <tr key={index}>
            <td>{element.ID}</td>
            <td>{element.fullname}</td>
            <td>{element.username}</td>
            <td><Moment format="DD/MM/YYYY">
                {element.birthDay}
            </Moment></td>
            <td>{element.address}</td>
            <td>{element.gender}</td>
            <td>{element.role}</td>
            <td>
            <Button variant="primary" className={styles.myButtonDetail} onClick={(event) => editItem(event, element)}><i className="fa fa-edit"></i></Button>
                <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick={() => deleteAccount(element)}><i className="fa fa-trash"></i></Button>
            </td>
        </tr>
    );

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>ID</th>
                    <th width={'15%'}>Tên</th>
                    <th width={'15%'}>Tên tài khoản</th>
                    <th width={'10%'}>Ngày sinh</th>
                    <th>Địa chỉ</th>
                    <th width={'10%'}>Giới tính</th>
                    <th width={'15%'}>Vai trò</th>
                    <th width={'8%'}>
                    </th>
                </tr>
            </thead>
            <tbody>
                {listItems}
            </tbody>
        </Table>
    );
}

const mapStateToProps = state => {
    return {

    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onDeleteAccount: (item) => {
            dispatch(actions.deleteAccount(item));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TableContainer);