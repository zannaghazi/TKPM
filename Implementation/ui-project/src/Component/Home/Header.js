import React from 'react';
import Logo from './Logo';
import SearchComponent from './SearchComponent'
import {Container, Col, Row} from 'react-bootstrap'
import LoginButton from './LoginButton'

function Header(props) {
    return (
        <Container>
            <Row>
                <Col lg={2}>
                    <Logo />
                </Col>
                <Col lg={1}>
                    
                </Col>
                <Col lg={7}>
                    <SearchComponent />
                </Col>
                <Col lg={2}>
                    <LoginButton />
                </Col>
            </Row>
        </Container>
    );
}

export default Header;