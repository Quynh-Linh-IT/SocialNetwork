import classNames from "classnames/bind";
import styles from "./CreatePost.module.scss";
import images from "~/assets/images";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEllipsisVertical } from "@fortawesome/free-solid-svg-icons";
function CreatePost() {
    const cx = classNames.bind(styles);
    return ( 
        <div className={cx('wrapper','w-full')}>
            <div className={cx('wrapper__inputPost','flex')}>
                <span className={cx('wrapper__inputPost-img','')}><img src={images.user} className={cx('w-20 h-20 rounded-full','selection:')} alt="User Post"/></span>
                <input className={cx('bg-background','wrapper__inputPost-input')} type="text" placeholder="Share your thoughts..." />
            </div>
            <div className={cx('wrapper__menuPost','flex items-center justify-between')}>
                <div className={cx('','flex items-center')}>
                    <div className={cx('wrapper__menuPost-uploadPhoto','flex items-center bg-background text-white')}>
                        <img className={cx('w-7 h-7')} src={images.uploadPhoto} alt="Upload Photo"/>
                        <span>Photo</span>
                    </div>
                    <div className={cx('wrapper__menuPost-uploadVideo','flex items-center bg-background text-white')}>
                        <img className={cx('w-7 h-7')} src={images.uploadVideo} alt="Upload Video"/>
                        <span>Video</span>
                    </div>
                    <div className={cx('wrapper__menuPost-uploadEvent','flex items-center bg-background text-white')}>
                        <img className={cx('w-7 h-7')} src={images.uploadEvent} alt="Upload Event"/>
                        <span>Event</span>
                    </div>
                    <div className={cx('wrapper__menuPost-uploadFeeling','flex items-center bg-background text-white')}>
                        <img className={cx('w-7 h-7')} src={images.uploadFeeling} alt="Upload Feeling"/>
                        <span>Feeling/Activity</span>
                    </div>
                </div>
                <div className={cx('bg-background text-white','wrapper__menuPost-more')}>
                    <FontAwesomeIcon icon={faEllipsisVertical}/>
                </div>
            </div>
        </div>
    );
}

export default CreatePost;