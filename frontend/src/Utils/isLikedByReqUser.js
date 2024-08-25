export const isLikedByReqUser = (requserId, post) => {
  for (let user of post.liked) {
    if (requserId === user.id) {
      return true;
    }
  }
  return false;
};
