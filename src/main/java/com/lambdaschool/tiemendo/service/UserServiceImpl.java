package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Role;
import com.lambdaschool.tiemendo.model.User;
import com.lambdaschool.tiemendo.model.UserRoles;
import com.lambdaschool.tiemendo.repository.RoleRepository;
import com.lambdaschool.tiemendo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Autowired
    private  UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        com.lambdaschool.tiemendo.model.User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public com.lambdaschool.tiemendo.model.User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    public List<com.lambdaschool.tiemendo.model.User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setUserRoles(user.getUserRoles());


        // ArrayList to hold new roles
        List<UserRoles> roleslist = new ArrayList<>();
        user.getUserRoles().iterator().forEachRemaining(u ->
        {
            u.setUser(newUser);
            Role r = rolerepos.findRoleByName(u.getRole().getName());
            u.setRole(r);
            roleslist.add(u);
        });

        newUser.setUserRoles(roleslist);

        User userSaved = userrepos.save(newUser);
        return userSaved;

//        Role r = rolerepos.findRoleByName("user");
//        newRoles.add(new UserRoles(newUser, r));
//
//        return userrepos.save(newUser);

    }

        //        for (UserRoles ur : user.getUserRoles())
//        {
//            newRoles.add(new UserRoles(newUser, ur.getRole()));
//        }
//        newUser.setUserRoles(newRoles);
//
//        return userrepos.save(newUser);

    @Transactional
    @Override
    public User update(User user, long id)
    {

        User currentUser = userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (currentUser != null)
        {

                System.out.print("*********"+id);
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());

                }

                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }

                if (user.getUserRoles().size() > 0)
                {

                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones

                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

                    // add the new ones
//                    List<UserRoles> roleslist = new ArrayList<>();
                    for (UserRoles ur : user.getUserRoles())
                    {
//                        ur.setUser(currentUser);
                        Role r = rolerepos.findRoleByName(ur.getRole().getName());
//                        ur.setRole(r);
//
//                        currentUser.getUserRoles().add(ur);
//
////
//                        System.out.println("ONE TIME THROUGH");
                        rolerepos.insertUserRoles(currentUser.getUserid(),r.getRoleid());
//                        when i take this away it says that the pk is null in userroles
                    }
//                    currentUser.setUserRoles(roleslist);
                    System.out.println("USER IT HAS UPDATED? "+currentUser.getUserid());
                }

                return userrepos.save(currentUser);


        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }
}

