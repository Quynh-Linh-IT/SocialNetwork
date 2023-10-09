import AuthForm from "~/components/form/auth/authForm";
import classNames from "classnames/bind";
import styles from "./Register.module.scss";
import 'tippy.js/dist/tippy.css'; 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleInfo } from "@fortawesome/free-solid-svg-icons";
import Tippy from "@tippyjs/react";
import { useState } from "react";
import { checkPasswordHeight, checkPasswordLow, checkPasswordMedium } from "~/const/checkPassWord";
function Register() {
    const cx = classNames.bind(styles);
    const [checkSafetyPassWord,setCheckSafetyPassWord] = useState('');
    const [logCheckPassWord,setLogCheckPassWord] = useState('Write your password...');

    // HANDLE CHANGE PASSWORD AND HANDLE LEVEL PASSWORD
    const handleChangePassword = (e)=> {
        const valuePass = e.target.value;
        if(valuePass ===''){
            setCheckSafetyPassWord('');
        } else {
            if(!checkPasswordLow(valuePass)){
                setCheckSafetyPassWord('container__content-strengthLevelLow');
                setLogCheckPassWord('Easy peasy!');
            } 
            if(checkPasswordMedium(valuePass)){
                setCheckSafetyPassWord('container__content-strengthLevelMedium');
                setLogCheckPassWord('That is a simple one!');
            }  
            if(checkPasswordHeight(valuePass)) {
                setCheckSafetyPassWord('container__content-strengthLevelHeight');
                setLogCheckPassWord('Yeah! that password rocks!');
            }
        }
    }
    
    return ( 
        <AuthForm title={'Sign up'} des={'Already have an account?'} toWith={'Sign in here'}>
            <div className={cx('container__content','px-20 py-10')}>
                <form method="POST">
                    <div className={cx('container__content-ipEmail')}>
                        <input className={cx('w-full')} type="email" placeholder="Enter Email"/>
                        <span className={cx('container__content-log')}>We'll never share your email with anyone else.</span>
                    </div>
                    <div className={cx('container__content-ipPassword')}>
                        <input 
                            className={cx('w-full')} 
                            type="password" 
                            placeholder="Enter Password"
                            onChange={handleChangePassword}
                        />
                        <div className={cx('container__content-strengthMeter')}>
                            <div className={cx('container__content-strengthLevel',checkSafetyPassWord ? checkSafetyPassWord : '')}></div>
                        </div>  
                        <div className={cx('flex items-center justify-between py-5')}>
                            <span className={cx('container__content-log')}>{logCheckPassWord}</span>
                            <Tippy
                            content='Include at least one uppercase, one lowercase, one special character, one number and 8 characters long.'
                            > 
                                <FontAwesomeIcon className={cx('text-primaryColor')} icon={faCircleInfo}/>
                            </Tippy>
                        </div>
                    </div>
                    <div className={cx('flex items-center','')}>
                        <input type="checkbox" className={cx('')}/>
                        <span className={cx('pl-5')}>Keep me signed in</span>
                    </div>
                    <button className={cx('container__content-btn')} type="submit">Sign me up</button>
                </form>        
            </div>
        </AuthForm>
    );
}

export default Register;