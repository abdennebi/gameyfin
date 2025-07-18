import {useAuth} from "Frontend/util/auth";
import {Avatar as NextUiAvatar} from "@heroui/react";

// @ts-ignore
const Avatar = ({...props}) => {
    const auth = useAuth();
    const username = getUsername();

    function getUsername() {
        if (props.username === undefined || props.username === null || props.username == "") {
            return auth.state.user?.username;
        }

        return props.username;
    }

    // TODO: Check if avatar can be loaded from SSO
    return (
        <NextUiAvatar
            showFallback
            src={`/images/avatar?username=${username}`}
            {...props}
        />
    );
}

export default Avatar;