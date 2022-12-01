package Controller.UserProfile;

import com.cops.ntsf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserProfileRedirectServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        String userId = request.getParameter("userId");
        User profileUser = new User(userId);
        profileUser.getUser();

        User user = null;
        if (session.getAttribute("User") == null) {

            if (profileUser.getUserType().equals("Driver")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Vehicle")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Pedestrian")) {
                response.sendRedirect("#" + userId);
            }

            user = (User) session.getAttribute("User");

        } else if (user.getUserType().equals("Driver")) {

            if (profileUser.getUserType().equals("Driver")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Vehicle")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Pedestrian")) {
                response.sendRedirect("#" + userId);
            }

        } else if (user.getUserType().equals("Vehicle")) {

            if (profileUser.getUserType().equals("Driver")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Vehicle")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Pedestrian")) {
                response.sendRedirect("#" + userId);
            }

        } else if (user.getUserType().equals("Pedestrian")) {

            if (profileUser.getUserType().equals("Driver")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Vehicle")) {
                response.sendRedirect("#" + userId);

            } else if (profileUser.getUserType().equals("Pedestrian")) {
                response.sendRedirect("#" + userId);
            }
        }
    }
}