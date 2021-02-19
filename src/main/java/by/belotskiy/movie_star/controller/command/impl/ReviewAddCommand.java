package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewAddCommand implements ActionCommand {
    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String text = request.getParameter(RequestParameterName.TEXT);
        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        Review review = new Review("", "", text, 0, Status.ACTIVE, userId, movieId);
        try {
            reviewService.addReview(review);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
    }
}
